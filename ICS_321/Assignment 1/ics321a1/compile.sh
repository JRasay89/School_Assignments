#!/bin/bash

# add your external libraries to the class path
classpath="."

cmd="javac -cp $classpath ics321/*.java"

echo $cmd
$cmd
