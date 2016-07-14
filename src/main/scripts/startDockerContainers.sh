#!/usr/bin/env bash
eval "$(docker-machine env default)"
docker run -d -p 4444:4444 --name selenium-hub selenium/hub
docker run -d -P -p 5900:5900 --link selenium-hub:hub selenium/node-chrome-debug
docker run -d -P -p 5901:5900 --link selenium-hub:hub selenium/node-firefox-debug

echo -n "Waiting for grid to load."
while ! curl http://192.168.99.100:4444/grid/console > /dev/null 2>&1
do
  echo -n "."
  sleep 1
done
echo " "
echo "Connected to grid successfully"
