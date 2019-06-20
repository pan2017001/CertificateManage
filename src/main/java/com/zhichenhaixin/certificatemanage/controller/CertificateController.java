package com.zhichenhaixin.certificatemanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.certificatemanage.service.CertificateService;
/**
 * 数字证书接口
 * @author pwl
 *
 */
@RestController
public class CertificateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);
	
	@Autowired
	CertificateService service;

	/**
	 *上传证书（新增、更新）
	 * @param json
	 * @return
	 */
	@PostMapping(value = "/uploadData")
	public JSONObject uploadCertificate(@RequestBody String json){
		
		return service.uploadData(json);
	}
	/**
	 * 查询（分页）
	 * @param json
	 * @return
	 */
	@PostMapping(value = "/queryData")
	public JSONObject queryData(@RequestBody String json){
		
		return service.queryData(json);
	}
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/queryDatabyId")
	public JSONObject queryDatabyId(@RequestBody String json){
		
		return service.queryDatabyId(json);
	}
	/**
	 * 根据ID删除记录
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/deleteDatabyId")
	public JSONObject deleteDatabyId(@RequestBody String json){
		
		return service.deleteDatabyId(json);
	}
	
	/**
	 * 上传配置文件到工程目录
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @author zhangbohu
	 */
/*	@PostMapping(value = "upload/upload")

	public JSONObject uploadFile(@RequestBody @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "请选择一个配置文件进行上传");
			// return "redirect:/uploadtest";
			return ResultMsgUtil.error("请选择一个配置文件进行上传!");
		}

		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(ResourceUtil.fileUploadPaht + file.getOriginalFilename());
			// Path path = Paths.get("/" + file.getOriginalFilename());
			// Path path = Paths.get("f:/" + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message", "上传配置文件成功! '" + file.getOriginalFilename() + "'");
			return ResultMsgUtil.success("恭喜你,上传配置文件成功!");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "上传配置文件失败");
			return ResultMsgUtil.error("上传配置文件失败!" + e.getMessage());
		}

	}
*/
	/**
	 * 下载配置文件到指定目录
	 * 
	 * @param res
	 * @author zhangbohu
	 */
	/*@GetMapping(value = "/downloadProperties")
	public JSONObject testDownload(HttpServletResponse res) {
		String fileName = "application.properties";
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(ResourceUtil.fileUploadPaht + fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
			return ResultMsgUtil.success("文件下载成功:地址" + ResourceUtil.fileUploadPaht);
		} catch (IOException e) {

			return ResultMsgUtil.error("文件下载失败" + e.getMessage());
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
*/
}
