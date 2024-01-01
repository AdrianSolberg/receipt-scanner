import { StyleSheet } from 'react-native';

import EditScreenInfo from '../../components/EditScreenInfo';
import { Text, View } from '../../components/Themed';
import { FlatList } from 'react-native-gesture-handler';

export default function TabThreeScreen() {
  return (
    <View style={styles.container}>
      <View style={styles.listColumns}>
        <Text>Date</Text>
        <Text>Total</Text>
      </View>
      <FlatList 
        style={styles.list}
        data={[
          {key: '22. december 2023', value: 200},
          {key: '20. december 2023', value: 450}
          ]} 
          renderItem={({item}) => {
            return (
            <View style={styles.listItem}>
              <Text>{item.key}</Text>
              <Text>{item.value},-</Text>
            </View>
            );
          }
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    marginTop: 30,
  },
  separator: {
    marginTop: 30,
    height: 1,
    width: '80%',
  },
  list: {
    width: '90%',
  },
  listColumns: {
    width: '90%',
    marginVertical: 5,
    flexDirection: 'row', 
    justifyContent: 'space-between',
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    paddingVertical: 10,
  },
  listItem: {
    marginVertical: 5,
    flexDirection: 'row', 
    justifyContent: 'space-between',
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    paddingVertical: 10,
  }
});
