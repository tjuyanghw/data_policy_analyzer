
## Data_policy_analyzer
The goal of Data Policy Analyzer(DPA) is to extract third-party  data  sharing  policies  from  an  SDK  ToS

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

We use seleimun, webdriver, scrapy framework to dynamic crawler the sentence in each Tos url. 
1. install seleimun
```
pip install selenium
```
2. install webdriver: we have already download the webdriver for chrome Version 81.0.4044.113. If you chrome version is inconsistent with this version, please re-download the webdriver for the right verion. Please check https://chromedriver.chromium.org/downloads 
```
DRIVER_BIN = "../webdriver/chromedriver"
```
3. install scrapy: It is an open source and collaborative framework for extracting the data you need from websites in a fast, simple, yet extensible way.
``` 
pip install scrapy=1.6.0
``` 
4. install commom NLP tools. (eg., nltk, allennlp, spacy,stanza)
``` 
pip install nltk/allennlp/spacy/stanza
``` 
5. install some useful util tool to process tree and graph structure. (eg., ete3,networkx)
``` 
pip install ete3/networkx
``` 
## Usage

Here we will explain how to run the each module for this system.

### Part One: web crawler 

The crawler will extract all the text from website and split them into sentence. Put urls you want to crawl in the file "domainList_developer.txt" and specify which folder you want to store the crawl results. Each url will generate a csv file that contains sentences of the webpage.

```
python3 execute.py -i domainList_developer.txt -o /Users/huthvincent/Desktop/

```

### Part Two: customized ner model
The ner model is a sensitive data extracter. You can input a sentence as a paramater, and it will show you the sentensive data entity in the sentence. 

```
python3 extract_sensitive_data.py -i "Do not store Twitter passwords."
```
The output is {'Twitter passwords'}

### Part Three: policy statement discovery
Put the pre-processed Tos docs under the folder "raw data/40_pre_processed_data", then run extract_policy_statement.py. The results will be in the folder "raw data/40_post_processed_data/policy_statement_discovery". The column "predict_label" will shown whether the sentence is related to third party data sharing.

```
python3 extract_policy_statement.py
```
### Part Four: condition extraction
After we found the sentences related to the data sharing and collection. We want to extract the condition of such usage. We run "conditionExtrection/condition_extractor.py" to extract such condition. 

```
python3 condition_extractor.py
```

## Authors

* **Yue Xiao** - *Initial work* - [PurpleBooth](https://github.com/xiaoyue10131748)

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

