# Orthopedic Department with RabbitMQ

## Requirements
* Python 3.x
* pika
* rabbitmq-server

## Installation
To set up environment, enter _orthopedic_department_ directory and run:

```bash
make setup
```

## Usage
To run admin employee run:
```bash
python admin.py
```
All of the logs will be saved into _logs.log_ file

To run doctor employee run:
```bash
python doctor.py <doctor_id>
```

To run technician employee run:
```bash
python technician.py <technician_id> <examintaion_type_1> <examination_type_2>
```
Available examination types:
* knee
* elbow
* hip

## Cleanup
To cleanup environment run:
```bash
make cleanup
```