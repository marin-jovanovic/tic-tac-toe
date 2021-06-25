package com.tictactoe.designpatterns.behavioral;

import java.util.List;

public class IteratorDP {

	// used for iterating over complex data structures

	interface SocialNetwork {
		ProfileIterator createFriendsIterator(String profileId);
		ProfileIterator createCoworkersIterator(String profileId);
	}

	class Facebook implements SocialNetwork {
		public ProfileIterator createFriendsIterator(String profileId) {
			return new FacebookIterator(this, profileId, "friends");
		}

		public ProfileIterator createCoworkersIterator(String profileId) {
			return new FacebookIterator(this, profileId, "coworkers");
		}

		public List<Profile> socialGraphRequest(String profileId, String type) {
			return null;
		}
	}

	interface ProfileIterator {
		Profile getNext();
		boolean hasMore();
	}

	class FacebookIterator implements ProfileIterator {
		Facebook facebook;
		String profileId;
		String type;

		int currentPosition;
		List<Profile> cache;

		FacebookIterator(Facebook facebook, String profileId, String type) {
			this.facebook = facebook;
			this.profileId = profileId;
			this.type = type;
		}

		void lazyInit() {
			if (cache == null) {
				cache = facebook.socialGraphRequest(profileId, type);
			}
		}

		public Profile getNext() {
			if (hasMore()) {
				currentPosition++;
				return cache.get(currentPosition);
			}

			return null;
		}

		public boolean hasMore() {
			lazyInit();
			return currentPosition < cache.size();
		}
	}

	class SocialSpammer {
		void send(ProfileIterator iterator, String message) {
			while (iterator.hasMore()) {
				Profile profile = iterator.getNext();
//				System.sendEmail(profile.getEmail(), message);
			}
		}
	}

	class Profile {
		String getId() {
			return null;
		}
	}

	class Application {
		SocialNetwork network;
		SocialSpammer spammer;

		void config() {
//			if working with Facebook
//				this.network = new Facebook()
//			if working with LinkedIn
//				this.network = new LinkedIn()

			this.network = new Facebook();

			this.spammer = new SocialSpammer();
		}

		void sendSpamToFriends(Profile profile) {
			ProfileIterator iterator = network.createFriendsIterator(profile.getId());
			spammer.send(iterator, "very important msg");
		}

		void sendSpamToCoworkers(Profile profile) {
			ProfileIterator iterator = network.createCoworkersIterator(profile.getId());
			spammer.send(iterator, "very important msg");
		}
	}



}
