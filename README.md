# borchk-java

Borchk java version
Cache testing 

After deploying the WAR the cache statistics can be invoked on the following endpoint 
cachestats: curl localhost:8080/1.0/api/cache

Which will return a json blob with statistics read from the cache. 
Example : 
{
  "result": "ok",
  "cache": [
    {
      "name": "BORCHK_CACHE",
      "size": 0,
      "averageGetTime": 2.4773858,
      "averagePutTime": 15.418188,
      "averageRemoveTime": 0,
      "cacheEvictions": 0,
      "cacheGets": 0,
      "cacheHitPercentage": 1.1772528,
      "cacheHits": 0,
      "cacheMisses": 0,
      "cacheMissPercentage": 0,
      "cachePuts": 0,
      "cacheRemovals": 0,
      "creationTime": "2020-09-01@10:25:38.288",
      "lastAccessTime": "2020-09-01@10:32:11.014",
      "lastUpdateTime": "2020-09-01@10:38:05.746"
    }
  ]
}


The service exposes an endpoint which takes an int and returns its textual representation. 
eg: 
curl "localhost:8080/1.0/api/numberstring/100
returns
{"onehundred", 100}.
The result is cached, which can be verified by using the above cache stats endpoint
The cache can then be filled by the below code.

for i in {0..50000}; do curl "localhost:8080/1.0/api/numberstring/$i" &  done

When the cache size reachs 14905, it starts evicting objs, which effectively means a cache size of 14905 is that max
