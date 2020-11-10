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
import com.mallxi.beandata.Essayinfo;
import com.mallxi.beandata.GoodInfo;
import com.mallxi.beandata.Lingquan;
import com.mallxi.beandata.Notice;
import com.mallxi.beandata.User;
import com.mallxi.beandata.Zixun;
import com.mallxi.repository.BannersRepository;
import com.mallxi.repository.ClientUserRepository;
import com.mallxi.repository.EssayRepository;
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
	private EssayRepository essayRepository;
	
	private UserService userService;
	private SendSMSService sendSMSService;
	
	private AppPushService appPushService;;
	

	private String strToken;
	
	@Autowired
	public AdminController(ZixunRepository zixunRepository, LingquanRepository lingquanRepository, GoodsRepository goodRepository,
			ClientUserRepository clientuserRepository, NoticeRepository noticeRepository,
			BannersRepository bannerRepository, UserRepository userRepository, EssayRepository essayRepository, UserService userService,
			SendSMSService sendSMSService, ThirdpartyRepository thirdpartyRepository, AppPushService appPushService) {
		
		this.userRepository = userRepository;
		this.clientuserRepository = clientuserRepository;
		this.bannerRepository = bannerRepository;
		this.noticeRepository = noticeRepository;
		this.goodRepository = goodRepository;
		this.essayRepository = essayRepository;
		
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
		
		model.addAttribute("essayscount", essayRepository.count());
		
		/*model.addAttribute("lingquancount", lingquanRepository.count());
		model.addAttribute("zixuncount", zixunRepository.count());*/
		LOGGER.info("access admin /");
		//strToken = TokenUtils.getToken("admin", "12345");
		//LOGGER.info("access admin token:" + strToken);
		return "admin/index";
	}

	/**
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
	 * good add
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
	 * good edit
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
	 * good edit
	 * 
	 * @param good
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
			}
			info.setDetailpic3(picUrl4);
		}
		

		goodRepository.save(info);
		return "redirect:/admin/goodinfo";
	}

	/**
	 * good del
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
	 * moveup good
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
	 * movedown good
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
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/essayinfo")
	public String adminEssayinfo(Model model) {
		model.addAttribute("essayinfo", true);
		//Iterable<GoodInfo> noticelist = goodRepository.findAll();
		
		Sort sort = new Sort(Sort.Direction.DESC, "updateby");
		Iterable<Essayinfo> essayinfolist = essayRepository.findAll(sort);
		
		model.addAttribute("essayinfolist", essayinfolist);
		return "admin/essayinfo";
	}

	/**
	 * essay add
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/essayinfo/add")
	public String essayinfoAdd(Model model) {
		
		Essayinfo info = new Essayinfo();

		if (goodRepository.count() <= 0) {
			info.setUpdateby(1L);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Essayinfo> list = essayRepository.findAll(sort);

			Essayinfo topInfo = (Essayinfo) list.iterator().next();
			info.setUpdateby(topInfo.getUpdateby() + 1);
		}
		
		info.setStatus(0L);
		
		info.setCreatetime(DateTimeUtils.getNowDateTime());
		
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		info.setEssayid(DateTimeUtils.createNowTimeId());
		info.setDetailpic1("");
		info.setDetailpic2("");
		info.setDetailpic3("");
		info.setDetailpic4("");
		
		model.addAttribute("essayinfoAdd", info);
		
		return "admin/essayinfoAdd";
	}

	/**
	 * essay edit
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/essayinfo/edit")
	public String essayinfoEdit(Model model, @RequestParam Long id) {
		model.addAttribute("essayinfoEdit", essayRepository.findOne(id));
		return "admin/essayinfoEdit";
	}

	/**
	 * essay edit
	 * 
	 * @param essay
	 * @return
	 */
	@PostMapping("/admin/essayinfo/edit")
	public String essayinfoSubmit(
			@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2,
			@RequestParam(value = "file3") MultipartFile file3, @RequestParam(value = "file4") MultipartFile file4, @ModelAttribute Essayinfo info) {
		
		info.setUpdatetime(DateTimeUtils.getNowDateTime());
		
		if (info.getStatus() == null) {
			info.setStatus(0L);
		} else {
			info.setStatus(1L);
		}

		if (!file1.isEmpty()) {

			String picUrl1 = "";
			
			try {
				InputStream input1 = file1.getInputStream();

				picUrl1 = AliCloudUtils.uploadStreamToCloud(input1, file1.getOriginalFilename());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
			}
			info.setDetailpic3(picUrl4);
		}
		

		essayRepository.save(info);
		return "redirect:/admin/essayinfo";
	}

	/**
	 * good del
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/essayinfo/del")
	public String delEssayinfo(Model model, @RequestParam Long id) {
		essayRepository.delete(id);
		return "redirect:/admin/essayinfo";
	}

	
	/**
	 * moveup good
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/essayinfo/moveup")
	public String moveUpEssayinfo(Model model, @RequestParam Long id) {
		
		if (essayRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Essayinfo> noticelist = essayRepository.findAll(sort);

			Essayinfo beforInfo = null;
			Essayinfo nowInfo = null;
			Essayinfo tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {

				tempInfo = (Essayinfo)iter.next();
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
				
				essayRepository.save(beforInfo);
				essayRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/essayinfo";
	}

	/**
	 * movedown essay
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/essayinfo/movedown")
	public String moveDownEssayinfo(Model model, @RequestParam Long id) {

		if (goodRepository.count() > 1) {

			Sort sort = new Sort(Sort.Direction.DESC, "updateby");
			Iterable<Essayinfo> noticelist = essayRepository.findAll(sort);

			Essayinfo afterInfo = null;
			Essayinfo nowInfo = null;
			Essayinfo tempInfo = null;
			Iterator iter = noticelist.iterator();
			while (iter.hasNext()) {
			    //System.out.print(it.next() + ",");
				tempInfo = (Essayinfo)iter.next();
				if (tempInfo.getId() == id) {
					nowInfo = tempInfo;
					break;
				}
			}
			
			if (iter.hasNext()) {
				afterInfo = (Essayinfo)iter.next();
			}
			
			long tempUpdateId = 0;
			if (afterInfo != null && nowInfo != null) {
				tempUpdateId = afterInfo.getUpdateby();
				afterInfo.setUpdateby(nowInfo.getUpdateby());
				nowInfo.setUpdateby(tempUpdateId);
				
				essayRepository.save(afterInfo);
				essayRepository.save(nowInfo);
			}
			
			
		}
		return "redirect:/admin/essayinfo";
	}
	
	
	
	/**
	 * banner
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
	 * banner add
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
	 * banner edit
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
	 * banner edit
	 * 
	 * @param banner
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
				return "error," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "error," + e.getMessage();
			}
			notice.setPicUrl(picUrl);
		}

		bannerRepository.save(notice);
		return "redirect:/admin/banner";
	}

	/**
	 * banner del
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
	 * banner caozuo
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
	 * moveup banner
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
	 * movedown banner
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
	 *  configuration
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/configuration")
	public String configuration(Model model) {
		return "admin/configuration";
	}

}
