# Spring boot gRPC

```bash
> grpcurl -d '{"times": 10}' --plaintext 0.0.0.0:9090  proto.PingApi.Get
# Response
{
  "message": [
    "Ping_0",
    "Ping_1",
    "Ping_2",
    "Ping_3",
    "Ping_4",
    "Ping_5",
    "Ping_6",
    "Ping_7",
    "Ping_8",
    "Ping_9"
  ]
}
```