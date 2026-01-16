up:
	docker-compose up --build -d

down:
	docker-compose down

remove:
	docker rm librarydb pgadmin4 library-api

remove-libraryapi:
	docker rm libraryapi