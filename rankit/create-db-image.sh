#!/bin/bash
docker run --name my-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
