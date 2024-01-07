interface Item {
    name: string;
    price: number;
}
  
interface Receipt {
    receipt: Item[];
}

export { Item, Receipt };