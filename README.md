-- drop table for dj index if it exists

DROP TABLE IF EXISTS dj_index;


-- create table for dj index

CREATE TABLE dj_index(
dj_index_id SERIAL PRIMARY KEY,
quarter integer not null,
stock varchar(10),
date date,
open REAL,
high REAL,
low REAL,
close REAL,
volume integer,
percent_change_price REAL,
percent_change_volume_over_last_wk REAL,
previous_weeks_volume integer,
next_weeks_open REAL,
next_weeks_close REAL,
percent_change_next_weeks_price REAL,
days_to_next_dividend integer,
percent_return_next_dividend REAL
)

=========================================

**Testing the CRUD endpoints**

=========================================

**1) Getting a Index by ticker**

Make a GET request to http://localhost:8080/api/dj/indexes?stock=AA
specifying the ticker at the end of the URL, e.g. AA

**2) Upload Dow Jones Dataset file**

Make a POST request to http://localhost:8080/api/dj/indexes/upload
specifying key as indexFile and value as file location
(under body  > form data)

**3) To add new Index** 

Make a POST request with the JSON body as shown below to
POST http://localhost:8080/api/dj/index

{
"quarter": "1",
"stock": "BB",
"date": "1/7/2011",
"open": "$15.82",
"high": "$16.72",
"low": "$15.78",
"close": "$16.42",
"volume": 239655616,
"percent_change_price": "3.79267",
"percent_change_volume_over_last_wk": "",
"previous_weeks_volume": 0,
"next_weeks_open": "$16.71",
"next_weeks_close": "$15.97",
"percent_change_next_weeks_price": "-4.42849",
"days_to_next_dividend": 26,
"percent_return_next_dividend": "0.182704"
}