package com.example.demo.entity;

/**
 * @author lorne
 * @date 2020/3/19
 * @description
 */
public enum State {

    /**
     * 创建初始化
     */
    CREATE(0),PAY(1);

    private int code;

    State(int code) {
        this.code = code;
    }

    public static State parser(int code){
        for(State state: values()){
            if(state.getCode()==code){
                return state;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public static void main(String[] args) {
        State state = parser(2);
        System.out.println(state);
    }
}
