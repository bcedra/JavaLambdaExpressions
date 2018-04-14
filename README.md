# JavaLambdaExpressions
Stream Processing using Lambda Expression

## Description 
#### 
A smart house has a set of sensors that can be used to record the behavior of a person who lives in that house.
The history log of the person's activity is stored as tuples (startTime, endTime, activityLabel),
where startTime and endTime are the date and time each activity started and ended in time.
The activity label is the type of activity performed by the person: departure, toilet, shower,
Sleep, Breakfast, Lunch, Dinner, Snack, Exchange / TV, Grooming.
The Files.txt file attached to Activities.txt contains a set of activity records for a certain amount of time.

## Tasks
####
1. Counts the distinct days that appear in the monitoring data.
2. Determines a <String, Integer> Map that maps for each distinctive action. Writes the resulting map to a text file.
3. Generates a Map data <Integer, Map <String, Integer >> that contains the activity. Counts for each day of the log (task number 2 applied for each day of the log) and writes the result.
4. Determines a data structure of the Map <String, DateTime> that maps for each activity the total duration calculated during the monitoring period. Filters activities with a total duration of more than 10 hours. Writes the result in a text file.
5. Filters activities that have 90% of monitoring samples with a duration of less than 5 minutes. Collects the results in a <String> List that contains only the unique activity names and writes the result in a text file.
