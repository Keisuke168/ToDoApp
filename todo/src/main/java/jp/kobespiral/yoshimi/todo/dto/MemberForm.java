package jp.kobespiral.yoshimi.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.kobespiral.yoshimi.todo.entity.Member;
import lombok.Data;

@Data
public class MemberForm {
    @Pattern(regexp = "[a-z0-9_\\-]{4,16}")
    String mid; // メンバーID．

    @NotBlank
    @Size(min = 1, max = 32)
    String name; // 名前

    public Member toEntity() {
        Member m = new Member(mid, name);
        return m;
    }
}