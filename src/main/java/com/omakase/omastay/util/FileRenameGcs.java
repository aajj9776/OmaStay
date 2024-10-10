package com.omakase.omastay.util;

import java.util.List;
import java.util.stream.Collectors;
public class FileRenameGcs {

	public static String checkSameFileName(
			String fileName, List<String> existingFileNames) {
		//인자인 fileName에서 활장자를 뺀 파일명 가려내자!
		//우선 "."의 위치를 알아내야 한다.
		int period = fileName.lastIndexOf(".");// test23.txt --> 6
		
		String f_name = fileName.substring(0, period);// test23
		String suffix = fileName.substring(period); // .txt

		// 기존 파일 이름 목록에서 확장자를 제거
        List<String> existingFileNamesWithoutExtension = existingFileNames.stream()
                .map(name -> name.substring(0, name.lastIndexOf(".")))
                .collect(Collectors.toList());
		
		//파일이 이미 있다면 파일명 뒤에 숫자를 붙이기 위해 변수를 하나 준비하자
		int idx = 1;
		
		while(existingFileNamesWithoutExtension.stream().anyMatch(existingFileName -> existingFileName.equals(f_name))) {
			StringBuffer sb = new StringBuffer();
			sb.append(f_name);
			sb.append(idx++);
			sb.append(suffix);
			
			fileName = sb.toString();// test231.txt
		}
		return fileName;
	}
}
