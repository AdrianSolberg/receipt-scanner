import { StyleSheet, Button, TouchableOpacity, Image } from 'react-native';

import { Text, View } from '../../components/Themed';
import { Camera, CameraType } from 'expo-camera';
import * as MediaLibrary from 'expo-media-library';
import { useEffect, useState, useRef } from 'react';
import { FontAwesome } from '@expo/vector-icons';
import { ImageEditor } from "expo-image-crop-editor";

export default function TabTwoScreen() {
  const [type, setType] = useState(CameraType.back);
  const [permission, setPermission] = useState<boolean | null>(null);
  const [image, setImage] = useState<String | null>(null);
  const cameraRef = useRef<Camera | null>(null);

  useEffect(() => {
    (async () => {
      MediaLibrary.requestPermissionsAsync();
      const cameraStatus = await Camera.requestCameraPermissionsAsync();
      setPermission(cameraStatus.status === 'granted');
    })();
  }, []);

  const takePicture = async () => {
    if (cameraRef.current != null) {
      try {
        const data = await cameraRef.current.takePictureAsync();
        console.log(data);
        setImage(data.uri);
      } catch (e) {
        console.log(e);
      }
    }
  }

  const saveImage = async (uri: string) => {
    if (image) {
      try {
        await MediaLibrary.createAssetAsync(uri);
        alert('Picture saved!');
        setImage(null);
      } catch (e) {
        console.log(e);
      }
    }
  }

  if (permission === false) {
    return <Text>No access to camera</Text>
  }

  return (
    <View style={styles.container}>
      {!image ?
      <Camera style={styles.camera} type={type} ref={cameraRef}>
        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={takePicture}>
            <FontAwesome name="camera" size={48} color="white" />
          </TouchableOpacity>
        </View>
      </Camera>
      :
      <View style={styles.container}>
        <ImageEditor
          visible={true}
          onCloseEditor={() => setImage(null)}
          imageUri={image.toString()}
          fixedCropAspectRatio={16 / 9}
          lockAspectRatio={false}
          minimumCropDimensions={{
            width: 50,
            height: 50,
          }}
          onEditingComplete={(result) => {
            saveImage(result.uri);
          }}
          mode="full"
        />
      </View>
      }
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  camera: {
    flex: 1,
    width: '100%',
  },
  buttonContainer: {
    flex: 1,
    flexDirection: 'row',
    backgroundColor: 'transparent',
    margin: 32,
  },
  button: {
    flex: 1,
    alignSelf: 'flex-end',
    alignItems: 'center',
  },
});