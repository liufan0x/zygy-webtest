package com.zygy.web.shrio;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;


public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
	private int maxCount = 5; //��ೢ�Դ���
	private Cache<String, AtomicInteger> passwordRetryCache;
	public MyHashedCredentialsMatcher(CacheManager cacheManager){
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String)token.getPrincipal();  
        //retry count + 1  
		AtomicInteger retryCount = passwordRetryCache.get(username);
		
		if(retryCount == null){
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		//���ȳ����׳��쳣
		if(retryCount.incrementAndGet()>maxCount){
			throw new ExcessiveAttemptsException();
		}
        boolean matches = super.doCredentialsMatch(token, info);
        //��֤ͨ�����Ƴ�����
        if(matches)
        	passwordRetryCache.remove(username);
        return matches;
	}

}
