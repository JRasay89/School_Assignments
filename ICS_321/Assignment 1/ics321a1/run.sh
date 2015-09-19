#!/bin/bash
classpath="."

cmd="java -cp $classpath ics321/Driver $@"

echo "$cmd"
$cmd
