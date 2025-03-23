#!/bin/sh

for app in cart products users
do
  cd "$app" || exit
  ./gradlew bootJar
  cd ..
done