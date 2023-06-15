@REM @echo off
doskey 0=start gradle clean bootTestRun
doskey 1=curl http://localhost:8181/customersPG
doskey 2=curl http://localhost:8181/customersFlask
doskey 3=curl http://localhost:8181/customersRedis/pino
doskey 4=curl -d "{\"id\": 9191,\"name\": \"Curlo\"}" -H "Content-Type: application/json" -X POST http://localhost:8181/customersKafka
