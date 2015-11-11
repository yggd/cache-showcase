package org.yggd.spring.cache

interface Service {

    /**
     * キャッシュ登録用インタフェースメソッド
     *
     * @param key キャッシュ時のキー
     * @return キャッシュ対象オブジェクト
     */
    CacheVO put(String key)

    /**
     * キャッシュ取得用インタフェースメソッド
     *
     * @param key キャッシュ対象のキー
     * @return キャッシュヒット時はキャッシュオブジェクト
     */
    CacheVO get(String key)
}