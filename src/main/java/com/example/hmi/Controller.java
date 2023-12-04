package com.kdn.apc;

import com.kdn.apc.repository.AppSqlSessionFactory;
import com.kdn.apc.repository.DataMapper;
import com.kdn.apc.repository.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {
    private ApplicationConfig ac = ApplicationConfig.getInstance();
    @FXML
    private Text currentTime;
    public void initialize() {
        // 타임라인 생성 (1초마다 업데이트)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateServerTime()));
        timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복

        // 애플리케이션이 시작되면 타임라인 시작
        timeline.play();
    }

    private void updateServerTime() {
        // 서버로부터 현재 시간을 가져오는 메서드 (실제로는 네트워크 요청이 필요)
        String serverTime = getServerTime();

        // UI 업데이트
        currentTime.setText(serverTime);
    }

    private String getServerTime() {
        // 실제로는 서버로부터 현재 시간을 가져오는 로직을 여기에 추가
        // 여기서는 간단하게 현재 로컬 시간을 사용
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(new Date());
    }
    private Map<Integer,List<Node>> getchildNodes(){
        //tb_map의 모든 노드 데이터를 읽어온다.
        SqlSession session = new AppSqlSessionFactory().getSqlSession(ac.getDataBaseConfig());
        DataMapper mapper = session.getMapper(DataMapper.class);
        List<Node> mapList = mapper.getAllNode();
        session.close();

        Map<Integer, List<Node>> result = new HashMap<>();
        int maxNodeLevel = ac.getMaxNodeLevel();
        List<Integer> parentList = Arrays.asList(0);

        for(int level=0 ; level<maxNodeLevel;level++){
            for(Node node:mapList){
                if(node.getiParent()==parentList.get(level)){
                    parentList.set(level+1, node.getiId());
                    break;
                }
            }
        }
        return null;
    }
}