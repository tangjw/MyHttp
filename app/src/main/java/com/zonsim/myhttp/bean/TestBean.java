package com.zonsim.myhttp.bean;

import java.util.List;

/**
 * Created by tang-jw on 2016/5/27.
 */
public class TestBean extends BaseResponseBean {
	
	
	private InfoBean info;
	
	public InfoBean getInfo() {
		return info;
	}
	
	public void setInfo(InfoBean info) {
		this.info = info;
	}
	
	public static class InfoBean {
		/**
		 * firstName : Brett
		 * lastName : McLaughlin
		 * email : aaaa
		 */
		
		private List<ProgrammersBean> programmers;
		/**
		 * firstName : Isaac
		 * lastName : Asimov
		 * genre : sciencefiction
		 */
		
		private List<AuthorsBean> authors;
		/**
		 * firstName : 安德顿
		 * lastName : Clapton
		 * instrument : guitar
		 */
		
		private List<MusiciansBean> musicians;
		
		public List<ProgrammersBean> getProgrammers() {
			return programmers;
		}
		
		public void setProgrammers(List<ProgrammersBean> programmers) {
			this.programmers = programmers;
		}
		
		public List<AuthorsBean> getAuthors() {
			return authors;
		}
		
		public void setAuthors(List<AuthorsBean> authors) {
			this.authors = authors;
		}
		
		public List<MusiciansBean> getMusicians() {
			return musicians;
		}
		
		public void setMusicians(List<MusiciansBean> musicians) {
			this.musicians = musicians;
		}
		
		public static class ProgrammersBean {
			private String firstName;
			private String lastName;
			private String email;
			
			public String getFirstName() {
				return firstName;
			}
			
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			
			public String getLastName() {
				return lastName;
			}
			
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			
			public String getEmail() {
				return email;
			}
			
			public void setEmail(String email) {
				this.email = email;
			}
		}
		
		public static class AuthorsBean {
			private String firstName;
			private String lastName;
			private String genre;
			
			public String getFirstName() {
				return firstName;
			}
			
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			
			public String getLastName() {
				return lastName;
			}
			
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			
			public String getGenre() {
				return genre;
			}
			
			public void setGenre(String genre) {
				this.genre = genre;
			}
		}
		
		public static class MusiciansBean {
			private String firstName;
			private String lastName;
			private String instrument;
			
			public String getFirstName() {
				return firstName;
			}
			
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			
			public String getLastName() {
				return lastName;
			}
			
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			
			public String getInstrument() {
				return instrument;
			}
			
			public void setInstrument(String instrument) {
				this.instrument = instrument;
			}
		}
	}
}
