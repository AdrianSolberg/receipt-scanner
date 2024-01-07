import { StyleSheet } from 'react-native';
import { useEffect, useState } from 'react';

import { Text, View } from '../../components/Themed';
import { FlatList } from 'react-native-gesture-handler';
import { Receipt, Item } from '../types';

export default function TabOneScreen() {
  const [receipts, setReceipts] = useState<Receipt[]>([]);
  const [topList, setTopList] = useState<any[]>([]);

  interface HashMap<Number> {
    [key: string]: number;
  }

  useEffect(() => {
    fetchDataFromApi();
  }, []);


  const fetchDataFromApi = async () => {
    try {
      const response = await fetch('http://localhost:8080/receiptscanner');
      const data: Receipt[] = await response.json();
      setReceipts(data);
      processReceipts();
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const processReceipts = () => {
    const itemMap: HashMap<number> = {};
    for (const receipt of receipts) {
      for (const item of receipt.receipt) {
        if (itemMap.hasOwnProperty(item.name)) {
          itemMap[item.name] += item.price;
        } else {
          itemMap[item.name] = item.price;
        }
      }
    }
    const itemArray = Object.entries(itemMap).map(([name, price]) => ({ name, price }));
    itemArray.sort((a, b) => b.price - a.price);
    setTopList(itemArray);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Most purchased items</Text>
      <View style={styles.separator} lightColor="#eee" darkColor="rgba(255,255,255,0.1)" />
      <View style={styles.listColumns}>
        <Text>Item</Text>
        <Text>Total</Text>
      </View>
      <FlatList 
        style={styles.list}
        data={topList} 
          renderItem={({item, index}) => {
            return (
            <View style={styles.listItem}>
              <Text>{index + 1}. {item.name}</Text>
              <Text>{item.price},-</Text>
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
