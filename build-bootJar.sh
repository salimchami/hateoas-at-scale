#!/bin/sh

for app in cart products users
do
  cd "$app" || exit
  ./gradlew clean bootJar
  cd ..
done