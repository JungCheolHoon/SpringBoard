package kr.kwangan2.springmvcboard.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.kwangan2.springmvcboard.domain.BoardAttachVO;
import kr.kwangan2.springmvcboard.mapper.BoardAttachMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
// DB파일과 내 디렉토리 파일을 비교해서 DB에 없는 내 디렉토리 파일을 주기적으로 삭제
public class FileCheckTask {
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	// 어제 날짜로 되어 있는 내 디렉토리 폴더 구하기
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		// 날짜 연산을 위해서 Calendar 객체 생성
		cal.add(Calendar.DATE, -1);
		// 하루를 뺀 날짜의 문자열을 구한다
		String str = sdf.format(cal.getTime());
		// -를 파일 구분자 (윈도우\\, 유닉스/) 로 변경해서 리턴
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron="0 0 2 * * *") // 초 분 시 일 월 요일 (년도) => 매일 새벽 2시
	public void checkFiles() throws Exception{
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		log.warn("File Check Task 작동!!");
		log.warn("============");
		log.warn(fileList);
		
		// 어제 날짜의 파일명들을 DB에서 추출
		
		// 각각의 파일명들에 대한 경로를 리스트로 저장
		List<Path> fileListPaths = fileList.stream().map(
				vo->Paths.get("c:/upload",vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName())).collect(Collectors.toList());
		
		fileList.stream().filter(vo->vo.isFileType() ==true).map(
				vo->Paths.get("c:/upload", vo.getUploadPath(),vo.getUuid()+"thumb_"+vo.getFileName())).forEach(
						p->fileListPaths.add(p));
		
		fileListPaths.forEach(p->log.warn(p));
		
		File targetDir = Paths.get("c:/upload",getFolderYesterDay()).toFile();
		
		File [] removeFiles = targetDir.listFiles(file-> fileListPaths.contains(file.toPath())==false);
		
		for(File file: removeFiles) {
			log.warn(file.getAbsoluteFile());
			file.delete();
		}
	}
	
}
