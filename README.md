Service that interacts with National Bank of the Republic of Belarus.
There're two endpoints:
  1) GET /api/status - checks if in-memory db has information about currencies rate at the specific date
     parameters:
       date - date of format yyyy-MM-dd or dd.MM.yyyy
     output:
       if processing result is Http 200 (OK) -
         information message that means in-memory db has information about this period
       if processing result is Http 404 (Not found) -
         information message that means there's no information in in-memory db about this period
             
  3) GET /api/fetch - gets information about currency rate at the specific date
       date - date of format yyyy-MM-dd or dd.MM.yyyy
       code - currency code (short abbreviation) like 'USD', 'EUR', 'RUB', ...
      if processing result is Http 200 (OK) -
         response entity structure is :
         {
            "Cur_ID": int,
            "Date": date,
            "Cur_Abbreviation": string,
            "Cur_Scale": int,
            "Cur_Name": string,
            "Cur_OfficialRate": double
          }
      if processing result is Http 404 (Not found) -
         information message that means that during fetching data from nbrb api ocured exeption (for example nbrb doesn't has information at requesting period or incorrect currency code)
