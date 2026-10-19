# Rozetka: mobile phones test task

Selenide + JUnit tests that open rozetka.com.ua, go to the Mobile Phones page and collect
names and prices of all devices on the first page and of devices with the "Top Sales" label.

## Requirements
- JDK 25
- Google Chrome
- Maven 3.9+

## How to run
    mvn clean test
    mvn test -Dtest='RozetkaTest#collectTopSalesPhones'    # a single test

## Output
Names and prices are printed to the console.
On a failure Selenide saves a screenshot and the page HTML to `build/reports/tests`.