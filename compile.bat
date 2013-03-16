cls
@if not exist classes mkdir classes
javac -sourcepath src -d classes src\*.java
@pause
