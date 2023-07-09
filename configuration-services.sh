#!/bin/sh

# Check if Docker Desktop is already running
if docker system info > /dev/null 2>&1; then
    echo "Docker Desktop is already running."
else
    echo "Starting Docker Desktop..."
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
fi
# Wait for Docker Desktop to start (adjust the sleep time if needed)
sleep 5s
# Set max_map_count inside WSL Docker Desktop
wsl -d docker-desktop -- sysctl -w vm.max_map_count=262144
sleep 5s