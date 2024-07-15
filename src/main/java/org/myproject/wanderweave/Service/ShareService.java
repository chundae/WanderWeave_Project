package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareService implements ShareService_ITF{

    @Autowired
    private ShareRepository shareRepository;
}
