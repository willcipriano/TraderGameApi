run:
	docker-compose up

stop:
	docker-compose stop

build:
	./mvnw clean
	docker-compose rm --stop --force -v
	docker-compose build --no-cache

test:
	./mvnw clean
	docker-compose rm --stop --force -v
	docker-compose build --no-cache
	docker-compose -f docker-compose.test.yml up --abort-on-container-exit

clean:
	./mvnw clean
	docker-compose rm --stop --force -v
