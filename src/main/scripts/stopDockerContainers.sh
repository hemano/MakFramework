#!/usr/bin/env bash
eval "$(docker-machine env default)"
docker stop $(docker ps -q)
docker rm $(docker ps -qa)
