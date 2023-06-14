doskey tcpg=curl http://localhost:8181/customersPG
doskey tcflask=curl http://localhost:8181/customersFlask
doskey tcredis=curl http://localhost:8181/customersRedis/pino
doskey tckafka=curl -d "{\"id\": 9191,\"name\": \"Curlo\"}" -H "Content-Type: application/json" -X POST http://localhost:8181/customersKafka
