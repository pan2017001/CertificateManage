package com.zhichenhaixin.certificatemanage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 文件上传路径从绝对路径转为HTTP路径
 * 
 * @author pwl
 *
 */
@Configuration
public class FileServerConfig extends WebMvcConfigurerAdapter {
	//外部文件路径
    @Value("${local.fileserver.dir}")
    private String localFileServerDir;
    //URL访问路径
    @Value("${local.fileserver.path}")
    private String localFileServerPath;
    //项目内部静态资源文件路径
    @Value("${local.static.path}")
    private String localStaticPath;

    /**
     * 重写访问路径映射成URL方式
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//可以配置多个路由
    	//配置外部文件路径转URL访问
        registry.addResourceHandler("/" + this.getLocalFileServerPath() + "/**").addResourceLocations("file:" + this.getLocalFileServerDir() + "/");
        //配置项目内部静态文件如js,css路径  防止html打开时，加载不到js,css
        registry.addResourceHandler("/" + this.getLocalFileServerPath() + "/**").addResourceLocations( this.getLocalStaticPath() + "/");

        super.addResourceHandlers(registry);
    }

    /**
     * 文件实际路径转为服务器url路径
     * @param absolutePath
     * @return
     */
    public String toServerPath(String absolutePath) {
        absolutePath = absolutePath.replaceAll("\\\\", "/");
        return "/" + absolutePath.replace(localFileServerDir, localFileServerPath);
    }

    public String getLocalFileServerDir() {
        return localFileServerDir;
    }

    public void setLocalFileServerDir(String localFileServerDir) {
        this.localFileServerDir = localFileServerDir;
    }

    public String getLocalFileServerPath() {
        return localFileServerPath;
    }

    public void setLocalFileServerPath(String localFileServerPath) {
        this.localFileServerPath = localFileServerPath;
    }

	public String getLocalStaticPath() {
		return localStaticPath;
	}

	public void setLocalStaticPath(String localStaticPath) {
		this.localStaticPath = localStaticPath;
	}
    
}
