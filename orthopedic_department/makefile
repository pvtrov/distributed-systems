# Makefile

.PHONY: setup cleanup

setup:
	@pip install -r requirements.txt
	@echo "Starting RabbitMQ server..."
	@systemctl start rabbitmq-server
	@echo "Running setup script..."
	@python setup.py

cleanup:
	@echo "Running cleanup script..."
	@python cleanup.py
	@echo "Stopping RabbitMQ server..."
	@systemctl stop rabbitmq-server
