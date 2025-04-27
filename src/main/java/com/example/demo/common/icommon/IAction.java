package com.example.demo.common.icommon;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.ui.Model;

/** ボタン押下時のアクションクラス */
public interface IAction {

	public enum ACTION {
		DISPLAY("display"), CLEAR("clear");

		ACTION(final String action) {
			this.action = action;
		}

		private final String action;

		public String getAction() {
			return this.action;
		}

		public static Optional<ACTION> getACTION(final String action) {
			return Arrays.stream(values())
					.filter(a -> a.action.equals(action))
					.findFirst();
		}
	}

	public void execute(Model model);
}
