
# Start database
With the docker already installed, run the follow command:

```bash
docker run --name mysql -p 3306:3306 -e "MYSQL_DATABASE=batch" -e "MYSQL_USER=batch" -e "MYSQL_PASSWORD=batch" -e "MYSQL_ALLOW_EMPTY_PASSWORD=false" -d mysql
```