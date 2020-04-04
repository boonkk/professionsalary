# professionsalary
  parsing csv/json files with data about employees and calculates sum of salaries for every occured profession

Program takes file paths as arguments.
Run with:
mvn clean package
java -jar target/SalariesCalculator.jar filepath1 filepath2

Program supports JSON and CSV files in format:
CSV format:
`...;job;...;salary;...
...; "Teacher";...;"3540,20";...
...; "Priest";...;"3469,42";...`



JSON format:
`{
  "employees": [
    {
	...
      "job": "Teacher",
	...
      "salary": "3540,20",
	...
    },
    ...
}`

or

`[
    {
	...
      "job": "Teacher",
	...
      "salary": "3540,20",
	...
    },
    ...
]
`

- works for files with no extension (e.g. "csvFile" instead of "csvFile.csv")
- job and salary columns/fields order doesn't matter, just need to be present in file
- supports ANY number of columns/fields (finds if 'job' and 'salary' are present)
- supports "ugly" CSV files e.g. "teAcHer" fields equals "Teacher", ignores whitespaces (...,"Teacher      "        ;....)
- supports salary values with commas or dots (1000,3 = 1000,3)
- data can be separated with semicolon(task condition) or comma(usual separator) (CSV file)
- double quotes doesn't matter (e.g. for ""Teacher"" value in file it will print Teacher without quotes)
- works for invalid data (eg. no job column, corrupted files, wrong number format (e.g. 0003,,44), not existing files etc.) - only prints message
(however can be easily changed to print what's already been counted or skip invalid line(CSV), in JSON file this would be a bit trickier, but its doable)
- numbers are printed with commas (1000,3)
