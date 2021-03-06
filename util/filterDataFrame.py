import pandas as pd
import glob



def filter_fn(row):
    if row['labelAPI'] == 'set()' or row['labelAPI'] == 'nan':
        return False
    if "void" in str(row["data_type"]) or "boolean" in str(row["data_type"]):
        return False

    if "here" in str(row["class_name"]) or "google" in str(row["class_name"]):
        return False
    if not str(row["method"]).startswith("get") or "getType()" in str(row["method"]):
        return False

    if "Deprecated" in str(row["class_description"]) or "Deprecated" in str(row["method_description"]) or is_Info(str(row["labelAPI"]) ) or 'boolean' in str(row["labelAPI"]):
        return False

    return True

def is_Info(s):
    if "{'information'}" in s.lower() or ("," not in s and "content" in s) or "the information" in s.lower() or "the name" in s.lower() or "the screen name" in s.lower() or "double" in s.lower() or "long" in s.lower() or "milliseconds" in s.lower() or "{'data'}" in s.lower():
        return True
    else:
        return False

def main():
    '''
    d = {
        'Name': ['Alisa', 'Bobby', 'jodha', 'jack', 'raghu', 'Cathrine',
                 'Alisa', 'Bobby', 'kumar', 'Alisa', 'Alex', 'Cathrine'],
        'Age': [26, 24, 23, 22, 23, 24, 26, 24, 22, 23, 24, 24],

        'Score': [85, 63, 55, 74, 31, 77, 85, 63, 42, 62, 89, 77]}

    df = pd.DataFrame(d, columns=['Name', 'Age', 'Score'])

    df = df.apply(filter_fn, axis=1, broadcast=True)

    print(df)
    '''



    xlsx_list = get_raw_file()
    print(xlsx_list)
    for filename in xlsx_list:
        if filename != "/Users/huthvincent/Documents/research/malicious_library_hunting/data_policy_analyzer/raw data/API_documents/40_API_documentations/data/labeled_API/total.xlsx":
            continue
        process(filename)

def process(filename):
    sheet = pd.read_excel(filename)
    df = sheet[sheet.apply(filter_fn, axis=1)]
    new_df = df.dropna(subset=["labelAPI","class_description"])
    to_filemame = "/Users/huthvincent/Documents/research/malicious_library_hunting/data_policy_analyzer/raw data/API_documents/40_API_documentations/data/filter_labeled_API/" + \
                  filename.split("40_API_documentations/data/labeled_API/")[1]
    new_df.to_excel(to_filemame, index=False, encoding="utf8",
                   header=["class_name", "class_description", "method", "method_description", "data_type",
                           "hump_expression", "is_hump","labelAPI"])



def get_raw_file():
    xlsx_list = glob.glob('/Users/huthvincent/Documents/research/malicious_library_hunting/data_policy_analyzer/raw data/API_documents/40_API_documentations/data/labeled_API/*.xlsx')
    print(u'have found %s csv files' % len(xlsx_list))
    print(u'正在处理............')
    return xlsx_list



if __name__ == "__main__":
    main()
