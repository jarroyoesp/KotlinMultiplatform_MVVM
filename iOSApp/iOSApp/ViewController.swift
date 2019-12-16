//
//  ViewController.swift
//  iOSApp
//
//  Created by Javier Arroyo on 11/12/2019.
//  Copyright © 2019 Javier Arroyo. All rights reserved.
//

import UIKit
import SharedCode

class ViewController: UIViewController {
    @IBOutlet weak var mButton: UIButton!
    @IBOutlet weak var mCounterLabel: UILabel!
    
    private var mCounterViewModel: CounterViewModel!
    private var mGitHubViewModel: GitHubViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configView()
        initViewModel()
    }
    
    func configView() {
        mButton.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)
    }
    
    func initViewModel() {
        mCounterViewModel = CounterViewModel()
        mGitHubViewModel = GitHubViewModel()
        observeCounterViewModel()
        observeGitHubViewModel()
    }
    
    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    func observeCounterViewModel() {
        mCounterViewModel.mGetCounterLiveData.addObserver { (mCurrentState) in
            if (mCurrentState is SuccessGetCounterState) {
                let successState = mCurrentState as! SuccessGetCounterState
                let response = (successState.response as! Response.Success)
                let value = response.data as! Int
                self.mCounterLabel.text = String(value)
                
            } else if (mCurrentState is LoadingGetCounterState) {
                self.mCounterLabel.text = "Loading"
            } else if (mCurrentState is ErrorGetCounterState) {
                self.mCounterLabel.text = "ERROR"
            }
        
        }
    }
    
    func observeGitHubViewModel() {
        mGitHubViewModel.mGetGitHubRepoListLiveData.addObserver { (mCurrentState) in
            if (mCurrentState is SuccessGetGitHubRepoListState) {
                let successState = mCurrentState as! SuccessGetGitHubRepoListState
                let response = (successState.response as! Response.Success)
                let value = response.data as! [GitHubRepo]
                self.onSuccessGetGitHubRepoList(list: value)
                
            } else if (mCurrentState is LoadingGetGitHubRepoListState) {
                self.mCounterLabel.text = "Loading"
            } else if (mCurrentState is ErrorGetGitHubRepoListState) {
                self.mCounterLabel.text = "ERROR"
            }
            
        }
    }
    
    func onSuccessGetGitHubRepoList(list: [GitHubRepo]) {
        self.mCounterLabel.text = list[0].name
    }
    
    /*
     * ON CLICKS
     */
    @objc func didButtonClick(_ sender: UIButton) {
        mCounterViewModel.getCounter()
        mGitHubViewModel.getGitHubRepoList(username: "jarroyoesp")
    }
    
    deinit {
        mCounterViewModel.onCleared()
    }
    
}


