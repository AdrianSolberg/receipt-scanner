import { StyleSheet } from 'react-native';
import { useEffect, useState } from 'react';

import { Text, View } from '../../components/Themed';
import { FlatList } from 'react-native-gesture-handler';
import { Receipt } from '../types';
import moment from 'moment';

export default function TabThreeScreen() {
  const [receipts, setReceipts] = useState<Receipt[]>([]);

  useEffect(() => {
    fetchDataFromApi();
  }, []);

  const fetchDataFromApi = async () => {
    try {
      const response = await fetch('http://localhost:8080/receiptscanner');
      const data: Receipt[] = await response.json();
      const processedReceipts: Receipt[] = data.map(receipt => ({
        ...receipt,
        getTotal: function() {
          return receipt.receipt.reduce((total, item) => total + item.price, 0);
        }
      }));
      setReceipts(processedReceipts);
    } catch (error) {
      console.error('Error fetching data:');
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.listColumns}>
        <Text>Scan Date</Text>
        <Text>Total</Text>
      </View>
      <FlatList 
        style={styles.list}
        data={receipts.slice().reverse()} 
          renderItem={({item: receipt}) => {
            const formattedDate = moment(receipt.date, 'MMM D, YYYY, h:mm:ss A').format('MMM D, YYYY, hh:mm A');
            return (
            <View style={styles.listItem}>
              <Text>{formattedDate}</Text>
              <Text>{receipt.getTotal()},-</Text>
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
