# Receipt Scanner

This is an application for scanning receipts. By using your phone camera and cropping the image to only contain the items on the receipt, the app will scan the items and save them. Based on you saved receipt, you will get statistics over your shopping habits. Currently, this entails a list over your most purshased items. the app also provides a log over your scanned receipts, so you can go back and check how much you spent on a given day.

## Table of Contents

- [Receipt Scanner](#receipt-scanner)
  - [Table of Contents](#table-of-contents)
  - [Project status](#project-status)
  - [Requirements](#requirements)
  - [Usage](#usage)

## Project status

This project is currently under development and is not yet considered finished. Contributions and feedback are welcome!

Missing parts:

- Connecting images taken to the scanning functionality in core, so that the data from the images can be saved and used for the statisics.

Warning: Name of automatic module 'tess4j' is unstable, it is derived from the module's file name. This might cause problems in the future.

## Requirements

These are the versions used during development, but later versions might work as well.

- Java version: 17.0.5
- Maven version: 3.9.4
- JUnit version: 5.7.2
- JaCoCo version 0.8.7
- Tess4J version: 5.9.0
- Node.js version: 18.16.1
- Expo CLI version 0.10.16

## Usage

To use the application you have to do the following:

1. Navigate to the Receipt-Scanner directory and run `mvn clean install`
2. After the build is successful, navigate to the springboot-directory and run `mvn spring-boot:run` to run the server
3. While the server is running, open a new terminal and navigate to the Receipt_Scanner folder within the react_ui-directory
4. When here, run the command `npx expo start`

If you want to use exampledata, move the file receipts.json from the example data folder to user.home on your computer. The application should then have some data preloaded.

If you want to use the Expo Go app to use the application on your phone, so that the camera works properly, you must change the link used in the fetch commands in both index.tsx and log.tsx.

Old link: `http://localhost:8080/receiptscanner`

New link format: `http://<ip-adress>:<port>/receiptscanner`

If you run the server, the data should show up when running the app on your phone.
