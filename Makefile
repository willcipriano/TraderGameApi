run:
	docker-compose down
	docker-compose up

clean:
	docker-compose down

test:
	docker-compose down
	docker-compose -f docker-compose.test.yml up --abort-on-container-exit