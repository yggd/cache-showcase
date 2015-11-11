package org.yggd.spring.cache

import groovy.util.logging.Slf4j
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable

@Slf4j
class ServiceImpl implements Service{

    @CachePut(value = 'cacheVO', key = '#key')
    @Override
    CacheVO put(String key) {
        new CacheVO(name: key, nowDate: new Date())
    }

    @Cacheable(value = 'cacheVO')
    @Override
    CacheVO get(String key) {
        // キャッシュがヒットしなかった
        log.error("cache miss,${key}")
    }
}
