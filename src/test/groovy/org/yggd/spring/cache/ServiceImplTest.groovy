package org.yggd.spring.cache

import groovy.util.logging.Slf4j
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification

@Slf4j
class ServiceImplTest extends Specification {

    def "キャッシュの格納・取得を行うサービスがprototypeであることの確認"() {
        when:
            def ctx = new ClassPathXmlApplicationContext('applicationContext.xml')
            def service1 = ctx.getBean 'service', Service
            def service2 = ctx.getBean 'service', Service
        then:
            log.debug "service1:${service1.hashCode()} service2:${service2.hashCode()}"
            assert service1.hashCode() != service2.hashCode()
    }

    def "prototypeのBeanからキャッシュされたものが取れることを確認"() {
        when:
            def ctx = new ClassPathXmlApplicationContext('applicationContext.xml')
            def service1 = ctx.getBean 'service', Service
            def service2 = ctx.getBean 'service', Service
            // サービス1でキャッシュ登録
            def vo1 = service1.put 'hoge1'
            // サービス2でキャッシュ取得
            def vo2 = service2.get 'hoge1'
        then:
            log.debug "service1.vo1:${vo1.name} date:${vo1.nowDate} hashCode:${vo1.hashCode()}"
            log.debug "service2.vo2:${vo2.name} date:${vo2.nowDate} hashCode:${vo2.hashCode()}"
            assert vo1.name == vo2.name
            assert vo1.nowDate == vo2.nowDate
            assert vo1.hashCode() == vo2.hashCode()
    }

    def "未登録のキャッシュはメソッドが呼ばれてしまうこと"() {
        when:
            def ctx = new ClassPathXmlApplicationContext('applicationContext.xml')
            def service1 = ctx.getBean 'service', Service
        then:
            // キャッシュがヒットしなかったらnullリターンで代用
            service1.get('no-cache') == null
    }

    def "複数キャッシュからgetのキー指定で目的のキャッシュを取得できること"() {
        when:
            def ctx = new ClassPathXmlApplicationContext('applicationContext.xml')
            def service1 = ctx.getBean 'service', Service
            def service2 = ctx.getBean 'service', Service
            def foo = service1.put('foo')
            def bar = service1.put('bar')
            def baz = service1.put('baz')
        then:
            assert foo == service2.get('foo')
            assert bar == service2.get('bar')
            assert baz == service2.get('baz')
    }
}
