package com.yuukiyg.springbootconsole.samplespringbootconsole.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuukiyg.springbootconsole.samplespringbootconsole.domain.Job;

@Service
@Transactional
public class EnService {

	public List<Job> searchJob() {

		String siteUrl;
		Document documents;
		Elements noJobMessage;
		Elements elementTotalJobCount;
		String siteName;
		Elements companyName;
		Elements jobType;
		String codingLanguages;
		Elements location;
		String phoneNumber;
		Elements businessDetails;
		Elements url;
		String firstUrl;
		String latterUrl;
		String published;
		
		Integer count = 1;
		Integer totalJobCount = 0;
		Integer displayCount = 50;
		Job job;
		List<Job> jobList = new ArrayList<>();

		String codingLanguage = null;
		List<String> codingLanguageNameList = Arrays.asList("Java", "Ruby", "PHP", "C++", "C#", "COBOL", "Go", "Kotlin", "Perl", "Python", "R", "Scala", "Swift", "TypeScript");
		
		// ■ 検索言語の設定(for1個目)
		try {
			for (int k = 0; k < codingLanguageNameList.size(); k++) {
				codingLanguage = codingLanguageNameList.get(k);

				// ■ for2個目
				for (int j = 1; j <= count; j++) {

//■ 検索URLの設定
					siteUrl = "https://employment.en-japan.com/search/search_list/?occupation_back=400000&caroute=0701&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&areaid=2&keywordtext="
							+ codingLanguage;
					if (count >= 2) {
						siteUrl = "https://employment.en-japan.com/search/search_list/?keywordtext=" + codingLanguage
								+ "&areaid=2&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&pagenum="
								+ j + "&aroute=0&arearoute=1&caroute=0701";
					}

//■ jsoupの実行
					documents = Jsoup.connect(siteUrl).get();

					noJobMessage = documents.select(".jobSearchListBase .jobSearchListLeftArea .zeroAnnounce .content .copy .none");
					// ■ 「条件にあてはまる求人情報がありませんでした。」が表示されなければ抽出
					if (noJobMessage.isEmpty()) {
//■ ページ数の取得
						elementTotalJobCount = documents.select(".jobSearchListBase .jobSearchListNumCondition .num em");

						totalJobCount = Integer.parseInt(elementTotalJobCount.first().text());

						float totalPage = totalJobCount / displayCount;
						if (totalJobCount / displayCount != 0) {
							totalPage++;
						}
						count = (int) totalPage;

//■ 抽出結果を要素ごとに分ける			
						siteName = "エン転職";
						companyName = documents.select(".nameSet .companyName .company");
						jobType = documents.select(".nameSet .jobName .jobNameText");

						codingLanguages = codingLanguage;
						location = documents.select(".dataArea .dataList");
						phoneNumber = "";

						businessDetails = documents.select(".dataArea .dataList");
						url = documents.select(".buttonArea .toDesc");
						firstUrl = "https://employment.en-japan.com";
						latterUrl = "&aroute=0&caroute=0701";

						published = "";

//■ Jobオブジェクトに格納(for3個目)
						for (int i = 0; i < companyName.size(); i++) {

							job = new Job();

							job.setSiteName(siteName);
							job.setCompanyName(companyName.get(i).text());
							job.setJobType(jobType.get(i).text());

							job.setCodingLanguages(codingLanguages);
							job.setLocation(location.get(i).children().last().children().last().text());
							job.setPhoneNumber(phoneNumber);

							job.setBusinessDetails(businessDetails.get(i).children().first().children().last().text());

							if (url.get(i).attr("href").contains("caroute=0701")) {
								job.setUrl(firstUrl + url.get(i).attr("href"));
							} else {
								job.setUrl(firstUrl + url.get(i).attr("href") + latterUrl);
							}
							job.setPublished(published);

//■ リストにJobオブジェクトを格納
							jobList.add(job);

						}// ■ for3個目の終わり
					}// ■ ifの終わり
				}// ■ for2個目の終わり
				count = 1;
			}// ■ for1個目の終わり
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jobList;
	}
}
