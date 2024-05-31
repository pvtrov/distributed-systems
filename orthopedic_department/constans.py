from enum import Enum

LOGGING_FILE = 'logs.log'


class TechnicianQueues(str, Enum):
    knee_queue = 'knee_queue'
    elbow_queue = 'elbow_queue'
    hip_queue = 'hip_queue'
    admin_queue = 'order_admin_queue'


class EmployeesQueues(str, Enum):
    doctor_1_queue = 'doctor_1_queue'
    doctor_2_queue = 'doctor_2_queue'
    technician_1_queue = 'technician_1_queue'
    technician_2_queue = 'technician_2_queue'
    admin_queue = 'admin_queue'


class ExchangesNames(str, Enum):
    orders = 'Medical examination orders'
    results = 'Medical examination results'
    broadcast_messages = 'Broadcast messages'


class Keys(str, Enum):
    knee_queue = 'knee'
    elbow_queue = 'elbow'
    hip_queue = 'hip'
    doctor_1_key = 'doctor_1'
    doctor_2_key = 'doctor_2'
    technician_1_key = 'technician_1'
    technician_2_key = 'technician_2'
    admin_key = 'admin'
