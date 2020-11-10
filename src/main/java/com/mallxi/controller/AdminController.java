package com.mallxi.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mallxi.beandata.BannerInfo;
import com.mallxi.beandata.ClientUser;
import com.mallxi.beandata.GoodInfo;
import com.mallxi.beandata.Lingquan;
import com.mallxi.beandata.Notice;
import com.mallxi.beandata.User;
import com.mallxi.beandata.Zixun;
import com.mallxi.repository.BannersRepository;
import com.mallxi.repository.ClientUserRepository;
import com.mallxi.repository.GoodsRepository;
import com.mallxi.repository.LingquanRepository;
import com.mallxi.repository.NoticeRepository;
import com.mallxi.repository.ThirdpartyRepository;
import com.mallxi.repository.UserRepository;
import com.mallxi.repository.ZixunRepository;
import com.mallxi.service.AppPushService;
import com.mallxi.service.SendSMSService;
import com.mallxi.service.UserService;
import com.mallxi.utils.AliCloudUtils;
import com.mallxi.utils.DateTimeUtils;

/**
 *
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	private UserRepository userRepository;
	private NoticeRepository noticeRepository;
	private ClientUserRepository clientuserRepository;
	private BannersRepository bannerRepository;
	private GoodsRepository goodRepository;
	private ThirdpartyRepository thirdpartyRepository;
	private ZixunRepository zixunRepository;
	private LingquanRepository lingquanRepository;
	
	private UserService userService;
	private SendSMSService sendSMSService;
	
	private AppPushService appPushService;;
	

	private String strToken;
	
	@Autowired
	public AdminController(ZixunRepository zixunRepository, LingquanRepository lingquanRepository, GoodsRepository goodRepository,
			ClientUserRepository clientuserRepository, NoticeRepository noticeRepository,
			BannersRepository bannerRepository, UserRepository userRepository, UserService userService,
			SendSMSService sendSMSService, ThirdpartyRepository thirdpartyRepository, AppPushService appPushService) {
		
		this.userRepository = userRepository;
		this.clientuserRepository = clientuserRepository;
		this.bannerRepository = bannerRepository;
		this.noticeRepository = noticeRepository;
		this.goodRepository = goodRepository;
		/*
		this.lingquanRepository = lingquanRepository;
		this.zixunRepository = zixunRepository;
		
	
		
		this.userService = userService;
		this.sendSMSService = sendSMSService;
		this.thirdpartyRepository = thirdpartyRepository;
		this.appPushService = appPushService;
		*/
	}

	/**
	 * 绠＄悊涓荤晫闈�
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/")
	public String adminIndex(Model model) {
		model.addAttribute("dashboard", true);
		model.addAttribute("userscount", clientuserRepository.count());
		model.addAttribute("bannerscount", bannerRepository.count());
		model.addAttribute("noticecount", noticeRepository.count());
		
		model.addAttribute("goodscount", goodRepository.count());
		
		/*model.addAttribute("lingquancount", lingquanRepository.count());
		model.addAttribute("zixuncount", zixunRepository.count());*/
		LOGGER.info("access admin /");
		//strToken = TokenUtils.getToken("admin", "12345");
		//LOGGER.info("access admin token:" + strToken);
		return "admin/index";
	}

	/**
	 * 鐢ㄦ埛绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user")
	public String adminUser(Model model) {
		model.addAttribute("user", true);
		return "admin/user";
	}

	/**
	 * 鐢ㄦ埛绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/clientuser")
	public String adminclientuser(Model model) {
		model.addAttribute("clientuser", true);
		Iterable<ClientUser> noticelist = clientuserRepository.findAll();
		model.addAttribute("clientuserlist", noticelist);
		
		//String userName = TokenUtils.getUserName(strToken);
		//LOGGER.info("access clientuser username:" + userName);
		
		return "admin/clientuser";
	}

	/**
	 * 鐢ㄦ埛澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/clientuser/add")
	public String clientuserAdd(Model model) {
		ClientUser user = new ClientUser();
		user.setUserid(DateTimeUtils.createNowTimeId());
		user.setCreatetime(DateTimeUtils.getNowDateTime());
		model.addAttribute("clientuserAdd", user);
		return "admin/clientuserAdd";
	}

	/**
	 * 鐢ㄦ埛淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/clientuser/edit")
	public String clientuserEdit(Model model, @RequestParam Long id) {
		model.addAttribute("clientuserEdit", clientuserRepository.findOne(id));
		return "admin/clientuserEdit";
	}

	/**
	 * 鐢ㄦ埛淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/clientuser/edit")
	public String clientuserSubmit(@ModelAttribute ClientUser user) {
		user.setUpdatetime(DateTimeUtils.getNowDateTime());
		
		clientuserRepository.save(user);
		return "redirect:/admin/clientuser";
	}

	/**
	 * 鐢ㄦ埛鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/clientuser/del")
	public String delClientuser(Model model, @RequestParam Long id) {
		clientuserRepository.delete(id);
		return "redirect:/admin/clientuser";
	}

	/**
	 * 閫氱煡绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/notice")
	public String adminNotice(Model model) {
		model.addAttribute("notice", true);
		
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<Notice> noticelist = noticeRepository.findAll(sort);
		
		model.addAttribute("noticelist", noticelist);
		return "admin/notice";
	}

	/**
	 * 閫氱煡澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/notice/add")
	public String noticeAdd(Model model) {
		//model.addAttribute("noticeAdd", new Notice());
		
		Notice info = new Notice();

		info.setCreatetime(DateTimeUtils.getNowDateTime());
		
		if (noticeRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Notice> bannerlist = noticeRepository.findAll(sort);

			Notice topBanerInfo = (Notice) bannerlist.iterator().next();
			info.setUpdateby(topBanerInfo.getUpdateby() + 1);
		}
		model.addAttribute("noticeAdd", info);
		
		return "admin/noticeAdd";
	}

	/**
	 * 閫氱煡淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/notice/edit")
	public String noticeEdit(Model model, @RequestParam Long id) {
		model.addAttribute("noticeEdit", noticeRepository.findOne(id));
		return "admin/noticeEdit";
	}

	/**
	 * 鐗╁搧淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/notice/edit")
	public String noticeSubmit(@ModelAttribute Notice notice) {
		
		notice.setUpdatetime(DateTimeUtils.getNowDateTime());

		noticeRepository.save(notice);
		return "redirect:/admin/notice";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/notice/del")
	public String delNotice(Model model, @RequestParam Long id) {
		noticeRepository.delete(id);
		return "redirect:/admin/notice";
	}

	
	/**
	 * moveup鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/notice/moveup")
	public String moveUpNotice(Model model, @RequestParam Long id) {
		
		if (bannerRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Notice> noticelist = noticeRepository.findAll(sort);

			Notice beforInfo = null;
			Notice nowInfo = null;
			Notice tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {

				tempInfo = (Notice)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				} else {
					beforInfo = tempInfo;
				}
				
			}
			long tempUpdateId = 0;
			if (beforInfo != null && nowInfo != null) {
				tempUpdateId = beforInfo.getUpdateby();
				beforInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				noticeRepository.save(beforInfo);
				noticeRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/notice";
	}

	/**
	 * movedown鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/notice/movedown")
	public String moveDownNotice(Model model, @RequestParam Long id) {

		if (noticeRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Notice> noticelist = noticeRepository.findAll(sort);

			Notice afterInfo = null;
			Notice nowInfo = null;
			Notice tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (Notice)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (Notice)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				noticeRepository.save(afterInfo);
				noticeRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/notice";
	}
	
	
	/**
	 * 閫氱煡绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/lingquan")
	public String adminLingquan(Model model) {
		model.addAttribute("lingquan", true);
		//Iterable<Lingquan> lingquanlist = lingquanRepository.findAll();
		
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<Lingquan> lingquanlist = lingquanRepository.findAll(sort);
		model.addAttribute("lingquanlist", lingquanlist);
		return "admin/lingquan";
	}

	/**
	 * 閫氱煡澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/lingquan/add")
	public String lingquanAdd(Model model) {
		//model.addAttribute("lingquanAdd", new Lingquan());
		
		Lingquan info = new Lingquan();

		if (lingquanRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Lingquan> bannerlist = lingquanRepository.findAll(sort);

			Lingquan topBanerInfo = (Lingquan) bannerlist.iterator().next();
			info.setUpdateby(topBanerInfo.getUpdateby() + 1);
		}
		model.addAttribute("lingquanAdd", info);
		
		return "admin/lingquanAdd";
	}

	/**
	 * 閫氱煡淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/lingquan/edit")
	public String lingquanEdit(Model model, @RequestParam Long id) {
		model.addAttribute("lingquanEdit", lingquanRepository.findOne(id));
		return "admin/lingquanEdit";
	}

	/**
	 * 鐗╁搧淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/lingquan/edit")
	public String lingquanSubmit(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "file1") MultipartFile file1, @ModelAttribute Lingquan lingquan) {

		lingquan.setDateupdate(DateTimeUtils.getNowDateTime());
		User user = userService.getCurrentUser();
		// notice.setUserId(user);

		if (lingquan.getStatusstr() != null && lingquan.getStatusstr().equals("涓婃灦")) {
			lingquan.setStatus(1L);
		} else {
			lingquan.setStatus(0L);
		}

		if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("鎺ㄨ崘")) {
			lingquan.setType(0L);
		} else if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("鏃ョ敤鍝�")) {
			lingquan.setType(1L);
		} else if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("鐙楃伯")) {
			lingquan.setType(2L);
		} else if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("鐚伯")) {
			lingquan.setType(3L);
		} else if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("婀跨伯")) {
			lingquan.setType(4L);
		} else if (lingquan.getTypestr() != null && lingquan.getTypestr().equals("淇濆仴鍝�")) {
			lingquan.setType(5L);
		} else {
			lingquan.setType(0L);
		}

		if (!file.isEmpty()) {

			String picUrl = "";// lastpicUrl;
			String dateStr = Long.toString(System.currentTimeMillis() / 1000L) + file.getOriginalFilename();
			try {
				InputStream input = file.getInputStream();

				picUrl = AliCloudUtils.uploadStreamToCloud(input, file.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			lingquan.setLeftpic(picUrl);
		}
		if (!file1.isEmpty()) {

			String picUrl1 = "";
			String dateStr1 = Long.toString(System.currentTimeMillis() / 1000L) + file1.getOriginalFilename();
			try {
				InputStream input1 = file1.getInputStream();

				picUrl1 = AliCloudUtils.uploadStreamToCloud(input1, file1.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			lingquan.setRightpic(picUrl1);
		}

		lingquanRepository.save(lingquan);
		return "redirect:/admin/lingquan";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/lingquan/del")
	public String delLingquan(Model model, @RequestParam Long id) {
		lingquanRepository.delete(id);
		return "redirect:/admin/lingquan";
	}

	/**
	 * moveup鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/lingquan/moveup")
	public String moveUpLingquan(Model model, @RequestParam Long id) {
		
		if (lingquanRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Lingquan> noticelist = lingquanRepository.findAll(sort);

			Lingquan beforInfo = null;
			Lingquan nowInfo = null;
			Lingquan tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {

				tempInfo = (Lingquan)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				} else {
					beforInfo = tempInfo;
				}
				
			}
			long tempUpdateId = 0;
			if (beforInfo != null && nowInfo != null) {
				tempUpdateId = beforInfo.getUpdateby();
				beforInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				lingquanRepository.save(beforInfo);
				lingquanRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/lingquan";
	}

	/**
	 * movedown鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/lingquan/movedown")
	public String moveDownLingquan(Model model, @RequestParam Long id) {

		if (lingquanRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Lingquan> noticelist = lingquanRepository.findAll(sort);

			Lingquan afterInfo = null;
			Lingquan nowInfo = null;
			Lingquan tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (Lingquan)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (Lingquan)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				lingquanRepository.save(afterInfo);
				lingquanRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/lingquan";
	}
	
	
	/**
	 * 閫氱煡绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/zixun")
	public String adminZixun(Model model) {
		model.addAttribute("zixun", true);
		//Iterable<Zixun> zixunlist = zixunRepository.findAll();
		
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<Zixun> zixunlist = zixunRepository.findAll(sort);
		
		model.addAttribute("zixunlist", zixunlist);
		return "admin/zixun";
	}

	/**
	 * 閫氱煡澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/zixun/add")
	public String zixunAdd(Model model) {
		//model.addAttribute("zixunAdd", new Zixun());
		
		Zixun info = new Zixun();

		if (zixunRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Zixun> bannerlist = zixunRepository.findAll(sort);

			Zixun topBanerInfo = (Zixun) bannerlist.iterator().next();
			info.setUpdateby(topBanerInfo.getUpdateby() + 1);
		}
		model.addAttribute("zixunAdd", info);
		
		return "admin/zixunAdd";
	}

	/**
	 * 閫氱煡淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/zixun/edit")
	public String zixunEdit(Model model, @RequestParam Long id) {
		model.addAttribute("zixunEdit", zixunRepository.findOne(id));
		return "admin/zixunEdit";
	}

	/**
	 * 鐗╁搧淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/zixun/edit")
	public String zixunSubmit(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2,
			@ModelAttribute Zixun zixun) {
		
		zixun.setDateupdate(DateTimeUtils.getNowDateTime());
		User user = userService.getCurrentUser();

		if (zixun.getStatusstr() != null && zixun.getStatusstr().equals("涓婃灦")) {
			zixun.setStatus(1L);
		} else {
			zixun.setStatus(0L);
		}

		if (zixun.getTypestr() != null && zixun.getTypestr().equals("鍟嗗闄�")) {
			zixun.setType(0L);
		} else if (zixun.getTypestr() != null && zixun.getTypestr().equals("褰辫鏂囧寲")) {
			zixun.setType(1L);
		} else if (zixun.getTypestr() != null && zixun.getTypestr().equals("闂ㄥ簵璇剧▼")) {
			zixun.setType(2L);
		} else if (zixun.getTypestr() != null && zixun.getTypestr().equals("绯荤粺鍩硅")) {
			zixun.setType(3L);
		} else if (zixun.getTypestr() != null && zixun.getTypestr().equals("鍔犵洘鏀跨瓥")) {
			zixun.setType(4L);
		} else {
			zixun.setType(0L);
		}

		if (!file.isEmpty()) {

			String picUrl = "";// lastpicUrl;
			String dateStr = Long.toString(System.currentTimeMillis() / 1000L) + file.getOriginalFilename();
			try {
				InputStream input = file.getInputStream();

				picUrl = AliCloudUtils.uploadStreamToCloud(input, file.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			zixun.setLeftpic(picUrl);
		}
		if (!file1.isEmpty()) {

			String picUrl1 = "";
			String dateStr1 = Long.toString(System.currentTimeMillis() / 1000L) + file1.getOriginalFilename();
			try {
				InputStream input1 = file1.getInputStream();

				picUrl1 = AliCloudUtils.uploadStreamToCloud(input1, file1.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			zixun.setRightpic(picUrl1);
		}
		if (!file2.isEmpty()) {

			String videoUrl = "";
			String dateStr2 = Long.toString(System.currentTimeMillis() / 1000L) + file2.getOriginalFilename();
			try {
				InputStream input2 = file2.getInputStream();

				videoUrl = AliCloudUtils.uploadStreamToCloud(input2, file2.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			zixun.setVideourl(videoUrl);
		}
		// notice.setUserId(user);
		zixunRepository.save(zixun);
		return "redirect:/admin/zixun";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/zixun/del")
	public String delZixun(Model model, @RequestParam Long id) {
		zixunRepository.delete(id);
		return "redirect:/admin/zixun";
	}

	/**
	 * moveup鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/zixun/moveup")
	public String moveUpZixun(Model model, @RequestParam Long id) {
		
		if (zixunRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Zixun> noticelist = zixunRepository.findAll(sort);

			Zixun beforInfo = null;
			Zixun nowInfo = null;
			Zixun tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {

				tempInfo = (Zixun)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				} else {
					beforInfo = tempInfo;
				}
				
			}
			long tempUpdateId = 0;
			if (beforInfo != null && nowInfo != null) {
				tempUpdateId = beforInfo.getUpdateby();
				beforInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				zixunRepository.save(beforInfo);
				zixunRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/zixun";
	}

	/**
	 * movedown鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/zixun/movedown")
	public String moveDownZixun(Model model, @RequestParam Long id) {

		if (zixunRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Zixun> noticelist = zixunRepository.findAll(sort);

			Zixun afterInfo = null;
			Zixun nowInfo = null;
			Zixun tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (Zixun)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (Zixun)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				zixunRepository.save(afterInfo);
				zixunRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/zixun";
	}
	
	/**
	 * 閫氱煡绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/goodinfo")
	public String adminGoodinfo(Model model) {
		model.addAttribute("goodinfo", true);
		//Iterable<GoodInfo> noticelist = goodRepository.findAll();
		
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<GoodInfo> goodinfolist = goodRepository.findAll(sort);
		
		model.addAttribute("goodinfolist", goodinfolist);
		return "admin/goodinfo";
	}

	/**
	 * 閫氱煡澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/goodinfo/add")
	public String goodinfoAdd(Model model) {
		//model.addAttribute("goodinfoAdd", new GoodInfo());
		
		GoodInfo info = new GoodInfo();

		if (goodRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<GoodInfo> bannerlist = goodRepository.findAll(sort);

			GoodInfo topBanerInfo = (GoodInfo) bannerlist.iterator().next();
			info.setUpdateby(topBanerInfo.getUpdateby() + 1);
		}
		
		info.setStatus(0L);
		
		info.setCreatetime(DateTimeUtils.getNowDateTime());
		
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		info.setProid(DateTimeUtils.createNowTimeId());
		info.setMainimage("");
		info.setDetailpic1("");
		info.setDetailpic2("");
		info.setDetailpic3("");
		info.setDetailpic4("");
		
		model.addAttribute("goodinfoAdd", info);
		
		return "admin/goodinfoAdd";
	}

	/**
	 * 閫氱煡淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/goodinfo/edit")
	public String goodinfoEdit(Model model, @RequestParam Long id) {
		model.addAttribute("goodinfoEdit", goodRepository.findOne(id));
		return "admin/goodinfoEdit";
	}

	/**
	 * 鐗╁搧淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/goodinfo/edit")
	public String noticeSubmit(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2,
			@RequestParam(value = "file3") MultipartFile file3, @RequestParam(value = "file4") MultipartFile file4, @ModelAttribute GoodInfo info) {
		
		info.setUpdatetime(DateTimeUtils.getNowDateTime());
		
		if (info.getRecommend() == null || info.getRecommend().equals("")) {
			info.setRecommend("0");
		} else {
			info.setRecommend("1");
		}
		if (info.getStatus() == null) {
			info.setStatus(0L);
		} else {
			info.setStatus(1L);
		}

		if (!file.isEmpty()) {

			String picUrl = "";// lastpicUrl;
			
			try {
				InputStream input = file.getInputStream();

				picUrl = AliCloudUtils.uploadStreamToCloud(input, file.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			info.setMainimage(picUrl);
		}
		if (!file1.isEmpty()) {

			String picUrl1 = "";
			
			try {
				InputStream input1 = file1.getInputStream();

				picUrl1 = AliCloudUtils.uploadStreamToCloud(input1, file1.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			info.setDetailpic1(picUrl1);
		}
		if (!file2.isEmpty()) {

			String picUrl2 = "";// lastpicUrl;
			
			try {
				InputStream input2 = file2.getInputStream();

				picUrl2 = AliCloudUtils.uploadStreamToCloud(input2, file2.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			info.setDetailpic2(picUrl2);
		}
		if (!file3.isEmpty()) {

			
			String picUrl3 = "";
			
			try {
				InputStream input3 = file3.getInputStream();

				picUrl3 = AliCloudUtils.uploadStreamToCloud(input3, file3.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			info.setDetailpic3(picUrl3);
		}
		
		if (!file4.isEmpty()) {

			String picUrl4 = "";// lastpicUrl;
			
			try {
				InputStream input4 = file4.getInputStream();

				picUrl4 = AliCloudUtils.uploadStreamToCloud(input4, file4.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			info.setDetailpic3(picUrl4);
		}
		

		goodRepository.save(info);
		return "redirect:/admin/goodinfo";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/goodinfo/del")
	public String delGoodinfo(Model model, @RequestParam Long id) {
		goodRepository.delete(id);
		return "redirect:/admin/goodinfo";
	}

	
	/**
	 * moveup鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/goodinfo/moveup")
	public String moveUpGoodinfo(Model model, @RequestParam Long id) {
		
		if (goodRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<GoodInfo> noticelist = goodRepository.findAll(sort);

			GoodInfo beforInfo = null;
			GoodInfo nowInfo = null;
			GoodInfo tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {

				tempInfo = (GoodInfo)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				} else {
					beforInfo = tempInfo;
				}
				
			}
			long tempUpdateId = 0;
			if (beforInfo != null && nowInfo != null) {
				tempUpdateId = beforInfo.getUpdateby();
				beforInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				goodRepository.save(beforInfo);
				goodRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/goodinfo";
	}

	/**
	 * movedown鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/goodinfo/movedown")
	public String moveDownGoodinfo(Model model, @RequestParam Long id) {

		if (goodRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<GoodInfo> noticelist = goodRepository.findAll(sort);

			GoodInfo afterInfo = null;
			GoodInfo nowInfo = null;
			GoodInfo tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (GoodInfo)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (GoodInfo)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				goodRepository.save(afterInfo);
				goodRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/goodinfo";
	}
	
	/**
	 * 閫氱煡绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/banner")
	public String adminBanner(Model model) {
		model.addAttribute("banner", true);
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<BannerInfo> bannerlist = bannerRepository.findAll(sort);
		model.addAttribute("bannerlist", bannerlist);
		return "admin/banner";
	}

	/**
	 * 閫氱煡澧炲姞琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/banner/add")
	public String bannerAdd(Model model) {

		BannerInfo info = new BannerInfo();
		
		if (bannerRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<BannerInfo> bannerlist = bannerRepository.findAll(sort);

			BannerInfo topBanerInfo = (BannerInfo) bannerlist.iterator().next();
			info.setUpdateby(topBanerInfo.getUpdateby() + 1);
		}
		
		info.setStatus(0L);
		
		info.setCreatetime(DateTimeUtils.getNowDateTime());
		
		model.addAttribute("bannerAdd", info);
		return "admin/bannerAdd";
	}

	/**
	 * 閫氱煡淇敼琛ㄥ崟
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/banner/edit")
	public String bannerEdit(Model model, @RequestParam Long id) {
		model.addAttribute("bannerEdit", bannerRepository.findOne(id));
		return "admin/bannerEdit";
	}

	/**
	 * 鐗╁搧淇敼鎻愪氦鎿嶄綔
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/banner/edit")
	public String bannerSubmit(@RequestParam(value = "file") MultipartFile file, @ModelAttribute BannerInfo notice) {
		
		notice.setUpdatetime(DateTimeUtils.getNowDateTime());
		String picUrl = "";// lastpicUrl;
		String dateStr = Long.toString(System.currentTimeMillis() / 1000L) + file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {

				InputStream input = file.getInputStream();

				picUrl = AliCloudUtils.uploadStreamToCloud(input, file.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "涓婁紶澶辫触," + e.getMessage();
			}
			notice.setPicUrl(picUrl);
		}

		bannerRepository.save(notice);
		return "redirect:/admin/banner";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/banner/del")
	public String delBanner(Model model, @RequestParam Long id) {
		bannerRepository.delete(id);
		return "redirect:/admin/banner";
	}

	/**
	 * 閫氱煡鍒犻櫎鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/banner/caozuo")
	public String caozuoBanner(Model model, @RequestParam Long id) {
		//bannerRepository.delete(id);
		
		BannerInfo info = bannerRepository.findOne(id);
		
		if (info != null) {
			if (info.getStatus() != 1) {
				info.setStatus(1L);
			} else {
				info.setStatus(0L);
			}
			bannerRepository.save(info);
		}
		
		
		return "redirect:/admin/banner";
	}
	
	
	/**
	 * moveup鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/banner/moveup")
	public String moveUpBanner(Model model, @RequestParam Long id) {
		
		if (bannerRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<BannerInfo> bannerlist = bannerRepository.findAll(sort);

			BannerInfo beforInfo = null;
			BannerInfo nowInfo = null;
			BannerInfo tempInfo = null;
			Iterator iter = bannerlist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (BannerInfo)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				} else {
					beforInfo = tempInfo;
				}
				
			}
			long tempUpdateId = 0;
			if (beforInfo != null && nowInfo != null) {
				tempUpdateId = beforInfo.getUpdateby();
				beforInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				bannerRepository.save(beforInfo);
				bannerRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/banner";
	}

	/**
	 * movedown鎿嶄綔
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/banner/movedown")
	public String moveDownBanner(Model model, @RequestParam Long id) {

		if (bannerRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<BannerInfo> bannerlist = bannerRepository.findAll(sort);

			BannerInfo afterInfo = null;
			BannerInfo nowInfo = null;
			BannerInfo tempInfo = null;
			Iterator iter = bannerlist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (BannerInfo)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (BannerInfo)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				bannerRepository.save(afterInfo);
				bannerRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/banner";
	}
	
	/**
	 * 鍚庡彴閰嶇疆绠＄悊
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/configuration")
	public String configuration(Model model) {
		return "admin/configuration";
	}

	/**
	 * 绗笁鏂归厤缃鐞�
	 * 
	 * @param model
	 * @return
	 */

	@GetMapping("/admin/thirdparty")
	public String thirdparty(Model model) {
		model.addAttribute("thirdparty", true);
		String smsUsername = null, smsPassword = null, pushAppID = null, pushAppKey = null, pushMasterSecret = null;
		try {
			smsUsername = thirdpartyRepository.findOne("smsUsername").getValue();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			smsPassword = thirdpartyRepository.findOne("smsPassword").getValue();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			pushAppID = thirdpartyRepository.findOne("pushAppID").getValue();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			pushAppKey = thirdpartyRepository.findOne("pushAppKey").getValue();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			pushMasterSecret = thirdpartyRepository.findOne("pushMasterSecret").getValue();
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (!sendSMSService.checkSmsAccountStatus(smsUsername, smsPassword)) {
			model.addAttribute("error", true);// 鐘舵�佷笉姝ｅ父
		}
		if (smsUsername == null && smsPassword == null) {
			model.addAttribute("init", true);// 绗竴娆″垵濮嬪寲
			model.addAttribute("error", false);
		}

		if (pushAppID == null && pushAppKey == null && pushMasterSecret == null) {
			model.addAttribute("initpush", true);// 绗竴娆″垵濮嬪寲
		}
		model.addAttribute("smsUsername", smsUsername);
		model.addAttribute("smsPassword", smsPassword);
		model.addAttribute("pushAppID", pushAppID);
		model.addAttribute("pushAppKey", pushAppKey);
		model.addAttribute("pushMasterSecret", pushMasterSecret);
		return "admin/thirdparty";
	}

	/**
	 * 鎻愪氦鐭俊骞冲彴璐﹀彿淇℃伅
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/admin/thirdpartysms")
	public String thirdpartysms(@RequestParam String username, @RequestParam String password) {
		sendSMSService.saveSmsConfig(username, password);
		if (sendSMSService.checkSmsAccountStatus(username, password)) {
			return "redirect:/admin/thirdparty?ok";// 璐﹀彿姝ｅ父
		} else {
			return "redirect:/admin/thirdparty?error";// 鐘舵�佷笉姝ｅ父
		}

	}

	/**
	 * 鎻愪氦鎺ㄩ�佸钩鍙颁俊鎭�
	 * 
	 * @param appID
	 * @param appKey
	 * @param masterSecret
	 * @return
	 */
	@PostMapping("/admin/thirdpartypush")
	public String thirdPartyPush(@RequestParam String pushAppID, @RequestParam String pushAppKey,
			@RequestParam String pushMasterSecret) {
		appPushService.savePushConfig(pushAppID, pushAppKey, pushMasterSecret);

		return "redirect:/admin/thirdparty?pok#push";
	}

	@PostMapping("/admin/thirdpartypushtest")
	public String thirdPartyPushTest(@RequestParam String title, @RequestParam String text,
			@RequestParam String openUrl) throws IOException {
		if (appPushService.sendPushMsg(title, text, openUrl)) {
			return "redirect:/admin/thirdparty?testok#push";
		} else {
			return "redirect:/admin/thirdparty?testerror#push";
		}

	}
}
