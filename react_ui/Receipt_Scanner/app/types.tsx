interface Item {
    name: string;
    price: number;
}
  
interface Receipt {
    receipt: Item[];
    date: Date;
    getTotal: () => number;
}

export { Item, Receipt };