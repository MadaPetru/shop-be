#!/bin/sh

/bin/bash ./configuration-services.sh

cd ./services/elk/ || exit
docker-compose up -d

cd ../prometheus/ || exit
docker-compose up -d

cd ../db/ || exit
docker-compose up -d

cd ../../
docker-compose up -d
