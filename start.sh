#!/bin/bash
for app in hateoas-library cart products users
do
  cd "$app" || exit
  rm -rf build
  ./gradlew clean build
  cd ..
done
docker compose up -d --build