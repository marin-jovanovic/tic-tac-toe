package com.tictactoe.designpatterns.structural;

public class ProxyDP {

	interface ThirdPartyYouTubeLib {
		String listVideos();
		String getVideoInfo(String id);
		void downloadVideo(String id);
	}

	static class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

		@Override
		public String listVideos() {
			System.out.println("ThirdPartyYouTubeClass; listVideos;");
			return "ThirdPartyYouTubeClass; listVideos;";
		}

		@Override
		public String getVideoInfo(String id) {
			System.out.println("ThirdPartyYouTubeClass; getVideoInfo;");
			return "ThirdPartyYouTubeClass; getVideoInfo;";
		}

		@Override
		public void downloadVideo(String id) {
			System.out.println("ThirdPartyYouTubeClass; downloadVideo;");
		}
	}

	static class CachedYouTubeClass implements ThirdPartyYouTubeLib {
		ThirdPartyYouTubeLib service;
		String listCache;
		String videoCache;
		boolean needRest;

		CachedYouTubeClass(ThirdPartyYouTubeClass service) {
			this.service = service;
			this.needRest = false;
		}

		@Override
		public String listVideos() {
			if (this.listCache == null || needRest) {
				this.listCache = this.service.listVideos();
			}

			return listCache;
		}

		@Override
		public String getVideoInfo(String id) {
			if (this.videoCache == null || needRest) {
				this.videoCache = this.service.getVideoInfo(id);
			}

			return this.videoCache;
		}

		@Override
		public void downloadVideo(String id) {
//			todo check if download exist
			if (needRest) {
				this.service.downloadVideo(id);
			}
		}
	}

	static class YouTubeManager {
		ThirdPartyYouTubeLib service;

		YouTubeManager(ThirdPartyYouTubeLib service) {
			this.service = service;
		}

		void renderVideoPage(String id) {
			String info = this.service.getVideoInfo(id);
		}

		void renderListPanel() {
			String list = this.service.listVideos();
		}

		void reactOnUserInput() {
			renderListPanel();
			renderListPanel();
		}
	}

	static class Application {
		void init() {
			ThirdPartyYouTubeClass aYoutubeService = new ThirdPartyYouTubeClass();
			CachedYouTubeClass aYoutubeProxy = new CachedYouTubeClass(aYoutubeService);
			YouTubeManager manager = new YouTubeManager(aYoutubeProxy);
			manager.reactOnUserInput();
		}

		public static void main(String[] args) {
			Application application = new Application();
			application.init();
		}
	}

}
