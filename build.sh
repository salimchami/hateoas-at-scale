#!/bin/sh

for app in cart products users
do
  cd "$app" || exit
  rm -rf build
  ./gradlew clean build
  cd ..
done